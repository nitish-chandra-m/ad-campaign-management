run:
	docker run --name local-postgres -e POSTGRES_PASSWORD=random123 -d -p 5432:5432 -v postgres_data:/var/lib/postgresql/data postgres