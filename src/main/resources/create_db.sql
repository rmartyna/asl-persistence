CREATE TABLE IF NOT EXISTS service (
	id SERIAL PRIMARY KEY,
	host VARCHAR(50) NOT NULL,
	port INTEGER NOT NULL,
	description VARCHAR(100),
	password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS cpu (
	id SERIAL PRIMARY KEY,
	service_id INTEGER,
	description VARCHAR(100),
	FOREIGN KEY (service_id) REFERENCES service (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cpu_temp (
	id SERIAL PRIMARY KEY,
	cpu_id INTEGER,
	core INTEGER NOT NULL,
	value NUMERIC(15,3) NOT NULL,
	date TIMESTAMP NOT NULL,
	FOREIGN KEY (cpu_id) REFERENCES cpu (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cpu_fan (
	id SERIAL PRIMARY KEY,
	cpu_id INTEGER,
	speed INTEGER NOT NULL,
	date TIMESTAMP NOT NULL,
	FOREIGN KEY (cpu_id) REFERENCES cpu (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cpu_usage (
  id SERIAL PRIMARY KEY,
  cpu_id INTEGER,
  "user" NUMERIC(15,3) NOT NULL,
  system NUMERIC(15,3) NOT NULL,
  iowait NUMERIC(15,3) NOT NULL,
  date TIMESTAMP NOT NULL,
  FOREIGN KEY (cpu_id) REFERENCES cpu (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS network_usage (
	id SERIAL PRIMARY KEY,
	service_id INTEGER,
	download NUMERIC(15,3) NOT NULL,
	upload NUMERIC(15,3) NOT NULL,
	date TIMESTAMP NOT NULL,
	FOREIGN KEY (service_id) REFERENCES service (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS disk (
	id SERIAL PRIMARY KEY,
	service_id INTEGER,
	description VARCHAR(100),
	FOREIGN KEY (service_id) REFERENCES service (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS partition (
	id SERIAL PRIMARY KEY,
	disk_id INTEGER,
	name VARCHAR(50) NOT NULL,
	current NUMERIC(15,3) NOT NULL,
	max NUMERIC(15,3) NOT NULL,
	date TIMESTAMP NOT NULL,
	FOREIGN KEY (disk_id) REFERENCES disk (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS disk_usage (
	id SERIAL PRIMARY KEY,
	disk_id INTEGER,
	read NUMERIC(15,3) NOT NULL,
	write NUMERIC(15,3) NOT NULL,
	date TIMESTAMP NOT NULL,
	FOREIGN KEY (disk_id) REFERENCES disk (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS memory_usage (
  id SERIAL PRIMARY KEY,
  service_id INTEGER,
  current NUMERIC(15,3) NOT NULL,
  max NUMERIC(15,3) NOT NULL,
  date TIMESTAMP NOT NULL,
  FOREIGN KEY (service_id) REFERENCES service (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS system_logs (
  id SERIAL PRIMARY KEY,
  service_id INTEGER,
  file_path TEXT NOT NULL,
  log TEXT NOT NULL,
  file_number INTEGER NOT NULL,
  date TIMESTAMP NOT NULL,
  FOREIGN KEY (service_id) REFERENCES service (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS console_configuration (
  id SERIAL PRIMARY KEY,
  service_id INTEGER,
  cpu_temp_lower_color NUMERIC(15,3) NOT NULL,
  cpu_temp_upper_color NUMERIC(15,3) NOT NULL,
  cpu_load_lower_color NUMERIC(15,3) NOT NULL,
  cpu_load_upper_color NUMERIC(15,3) NOT NULL,
  ram_load_lower_color NUMERIC(15,3) NOT NULL,
  ram_load_upper_color NUMERIC(15,3) NOT NULL,
  network_dl_lower_color NUMERIC(15,3) NOT NULL,
  network_dl_upper_color NUMERIC(15,3) NOT NULL,
  network_ul_lower_color NUMERIC(15,3) NOT NULL,
  network_ul_upper_color NUMERIC(15,3) NOT NULL,
  disk_read_lower_color NUMERIC(15,3) NOT NULL,
  disk_read_upper_color NUMERIC(15,3) NOT NULL,
  disk_write_lower_color NUMERIC(15,3) NOT NULL,
  disk_write_upper_color NUMERIC(15,3) NOT NULL,
  disk_usage_lower_color NUMERIC(15,3) NOT NULL,
  disk_usage_upper_color NUMERIC(15,3) NOT NULL,
  FOREIGN KEY (service_id) REFERENCES service (id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS service_configuration (
  id SERIAL PRIMARY KEY,
  service_id INTEGER,
  mode TEXT NOT NULL,
  poll_rate INTEGER NOT NULL,
  cpu_frequency INTEGER NOT NULL,
  cpu_enabled INTEGER NOT NULL,
  disk_frequency INTEGER NOT NULL,
  disk_enabled INTEGER NOT NULL,
  memory_frequency INTEGER NOT NULL,
  memory_enabled INTEGER NOT NULL,
  network_frequency INTEGER NOT NULL,
  network_enaled INTEGER NOT NULL,
  syslog_frequency INTEGER NOT NULL,
  syslog_enabled INTEGER NOT NULL,
  syslog_list TEXT NOT NULL,
  FOREIGN KEY (service_id) REFERENCES service (id)
       ON DELETE CASCADE
);