resource "aws_vpc" "main_vpc" {
  cidr_block = "10.0.0.0/16"

  tags = {
    Name = "taskflow-vpc"
  }
}

resource "aws_subnet" "public_subnet" {
  vpc_id                  = aws_vpc.main_vpc.id
  cidr_block              = "10.0.1.0/24"
  map_public_ip_on_launch = true

  tags = {
    Name = "taskflow-public-subnet"
  }
}

resource "aws_internet_gateway" "igw" {
  vpc_id = aws_vpc.main_vpc.id

  tags = {
    Name = "taskflow-igw"
  }
}

resource "aws_route_table" "public_rt" {
  vpc_id = aws_vpc.main_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.igw.id
  }

  tags = {
    Name = "taskflow-public-rt"
  }
}

resource "aws_route_table_association" "rta" {
  subnet_id      = aws_subnet.public_subnet.id
  route_table_id = aws_route_table.public_rt.id
}

resource "aws_s3_bucket" "taskflow_bucket" {
  bucket = "taskflow-cloud-bucket-cephora-202600"

  tags = {
    Name = "taskflow-s3"
  }
}

resource "aws_s3_bucket_server_side_encryption_configuration" "taskflow_bucket_encryption" {
  bucket = aws_s3_bucket.taskflow_bucket.id

  rule {
    apply_server_side_encryption_by_default {
      sse_algorithm = "AES256"
    }
  }
}

resource "aws_security_group" "taskflow_sg" {
  name        = "taskflow-sg"
  description = "Allow SSH"
  vpc_id      = aws_vpc.main_vpc.id

  ingress {
    description = "SSH access"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    description = "Allow outbound traffic"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "taskflow-sg"
  }
}

resource "aws_instance" "taskflow_ec2" {
  ami                         = "ami-00e1181affe35cfd8"
  instance_type               = "t3.micro"
  subnet_id                   = aws_subnet.public_subnet.id
  key_name                    = "taskflow-key"
  associate_public_ip_address = true

  vpc_security_group_ids = [aws_security_group.taskflow_sg.id]

  tags = {
    Name = "taskflow-ec2"
  }
}

resource "aws_db_instance" "taskflow_db" {
  allocated_storage   = 20
  engine              = "mysql"
  engine_version      = "8.0"
  instance_class      = "db.t3.micro"
  db_name             = "taskflowdb"
  username            = "admin"
  password            = "Taskflow123!"
  publicly_accessible = true
  skip_final_snapshot = true

  tags = {
    Name = "taskflow-rds"
  }
}