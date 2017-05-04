create table tipo_documento(
tp_id_documento SERIAL primary key,
tp_codigo varchar(20),
tp_nombre varchar(20)
);


INSERT INTO tipo_documento (tp_codigo,tp_nombre) VALUES ('C.C.','Cedula de Ciudadania');
INSERT INTO tipo_documento (tp_codigo,tp_nombre) VALUES ('T.I.','Tarjeta de Identidad');


create table personal(
identificacion_personal varchar(10) primary key,
nombre varchar(20),
apellido varchar(20),
tp_documento int,
    foreign key (tp_documento) references tipo_documento(tp_id_documento)
);

INSERT INTO personal (identificacion_personal,nombre,apellido,tp_documento) VALUES ('1115076109','Pepito','Perez',1);
INSERT INTO personal (identificacion_personal,nombre,apellido,tp_documento) VALUES ('1115076133','Carlos','Tello',1);
INSERT INTO personal (identificacion_personal,nombre,apellido,tp_documento) VALUES ('1115076165','Juan','Paez',2);
