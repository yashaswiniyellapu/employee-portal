
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.0"
    }
  }
}
provider "aws" {
      region = "us-east-1"
      access_key = "AKIA55FVHWUZN4NXLKU4"
      secret_key = "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"
}
resource "tls_private_key" "ssh" {
  algorithm = "RSA"
  rsa_bits  = 4096
}
resource "local_file" "private_key" {
  content = tls_private_key.ssh.private_key_pem
  filename = "deployer_public_key.pem"
  file_permission = "0600"
}
resource "aws_key_pair" "deployer" {
  key_name   = "deployer_public_key"
  public_key = tls_private_key.ssh.public_key_openssh
}
resource "aws_instance" "employee-portal" {
  ami           = "ami-09d56f8956ab235b3" 
  instance_type = "t2.micro"
  vpc_security_group_ids   = [aws_security_group.main.id]
  associate_public_ip_address = true 
  key_name = aws_key_pair.deployer.key_name
connection {
    type     = "ssh"
    user     = "ubuntu"
    host     = aws_instance.employee-portal.public_ip
    private_key = tls_private_key.ssh.private_key_pem
    host_key = tls_private_key.ssh.public_key_openssh
    agent = false
    port = 22
  }
provisioner "remote-exec" {
    inline = ["mkdir yashu"]
    }
  tags = {
     Name = "employee-portal"
 }
  
}
resource "aws_security_group" "main" {
  ingress {
    description      = "SSH"
    from_port        = 22
    to_port          = 22
    security_groups  = []
    protocol         = "tcp"
    cidr_blocks      = [ "0.0.0.0/0", ]
  }
 ingress {
    description      = "front-end"
    from_port        = 3000
    to_port          = 3000
    security_groups  = []
    protocol         = "tcp"
    cidr_blocks      = [ "0.0.0.0/0", ]
  } 
  ingress {
    description      = "backend"
    from_port        = 8080
    to_port          = 8080
    security_groups  = []
    protocol         = "tcp"
    cidr_blocks      = [ "0.0.0.0/0", ]
  }
  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = []
  }
}
resource "null_resource" "run-ansible" {
  provisioner "local-exec" {
    command = "ansible-playbook -i ${aws_instance.employee-portal.public_ip}, Deploy.yml"
  }
}
output "public_ip" {
value = aws_instance.employee-portal.public_ip  
}