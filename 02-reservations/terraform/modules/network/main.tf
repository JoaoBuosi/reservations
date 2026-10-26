variable "name" { type=string }
resource "aws_vpc" "this" { cidr_block="10.20.0.0/16"; tags={Name=var.name} }
resource "aws_subnet" "public" { vpc_id=aws_vpc.this.id; cidr_block="10.20.1.0/24"; map_public_ip_on_launch=true; availability_zone="${data.aws_availability_zones.available.names[0]}"; tags={Name="${var.name}-public"} }
data "aws_availability_zones" "available" { state="available" }
output "vpc_id" { value=aws_vpc.this.id }
