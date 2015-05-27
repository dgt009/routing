DROP DATABASE ;
CREATE DATABASE test;
\c test;
CREATE TABLE test_vessels(
	vessel_id integer NOT NULL,
	create_date timestamptz,
	update_date timestamptz,
	vessel_type text,
	ship_id integer,
	imo integer,
	mmsi text,
	call_sign text,
	vessel_name text,
	vessel_ex_name text,
	vessel_ex_name_2 text,
	length numeric,
	width numeric,
	grt integer,
	flag text,
	vessel_status text,
	avg_speed numeric
);

insert into test_vessels values ('106955',' 2013-07-31 14:45:40.263666+02','2013-07-31 14:45:40.263666+02','general_cargo_ship',1,7128851,'A3CH6','','HARENGUS','','',1,1,1,'','',1);



