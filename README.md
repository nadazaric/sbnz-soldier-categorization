<h1>Dutyify</h1>
Dutyify is a software that automates the categorization of soldiers and the selection of candidates for state benefits based on prescribed laws.

<h2>How to run</h2>

### Back
- Install the `model` application (`kjar` and `service` do not need to be installed).
- In the terminal, inside the database folder, run the command `docker compose up` to start the PostgreSQL database.
- Run ServiceApplication.java.
- Note: For the initial startup, to populate the database with initial data, set `ddl-auto=create` and `sql.init.mode=always` in the `application.properties` file in service aplication. After the first run, you can set `ddl-auto=update` and comment out the `sql.init.mode=always` line if you want to preserve the data added during the initial startup.

### Front
- Inside the `front` folder, run the command `npm run dev` to start up the frontend application.
- Go to `localhost:3000` in your browser and enjoy the application.

<h2>Credentials</h2>

#### Admin
- Username: admin
- Password: admin

#### Worker
- Username: ranka
- Password: ranka
