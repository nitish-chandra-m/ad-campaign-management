run:
	docker run --name local-postgres -e POSTGRES_PASSWORD=random123 -d -p 5432:5432 -v postgres_data:/var/lib/postgresql/data postgres
	-and docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management