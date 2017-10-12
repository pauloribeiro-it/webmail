drop database webmail;
create database webmail;
use webmail;
create table usuario 
( id integer auto_increment,
  nome varchar(150),
  email varchar(100),
  data_criacao timestamp,
  constraint pk_usuario primary key (id)
);

CREATE TABLE login(
	login varchar (100),
	senha varchar (100),
    id_usuario integer,
    constraint pk_user primary key(login),
    constraint fk_login_usuario foreign key (id_usuario) references usuario(id)
);

create table filtro
(
	id integer auto_increment,
    nome varchar(30),
    constraint pk_filtro primary key(id)
);

create table email
(
  id integer auto_increment,
  assunto varchar(100),
  corpo varchar(2000),
  id_remetente integer,
  status_email integer,
  data_hora_criacao timestamp,
  data_hora_lido timestamp,
  data_hora_recebido timestamp,
  data_hora_excluido timestamp,
  data_hora_deletado timestamp,
  data_hora_enviado timestamp,
  id_filtro integer,
  constraint pk_email primary key (id),
  constraint fk_email_remetente foreign key(id_remetente) references usuario(id),
  constraint fk_email_filtro foreign key(id_filtro) references filtro(id)
);

create table email_destinatario
(
  id_email integer,
  id_destinatario integer,
  is_cc boolean,
  is_cco boolean,
  constraint pk_email_destinatario primary key (id_email,id_destinatario),
  constraint fk_email_destinatario_email foreign key (id_email) references email(id),
  constraint fk_email_destinatario_usuario foreign key (id_destinatario) references usuario(id)
);

create table auditoria_login(
  id integer auto_increment,
  id_sessao varchar(20),
  id_usuario integer,
  data_login timestamp,
  constraint pk_auditoria_login primary key (id),
  constraint fk_auditoria_login_usuario foreign key(id_usuario) references usuario(id)
);

create table auditoria_operacao(
  id integer auto_increment,
  id_auditoria_login integer,
  desc_operacao varchar(200),
  data_operacao timestamp,
  constraint pk_auditoria_operacao primary key(id),
  constraint fk_auditoria_login foreign key(id_auditoria_login) references auditoria_login(id)
);

insert into filtro(id,nome) values(1,'Caixa de Entrada');
insert into filtro(id,nome) values(2,'Lixo');
insert into filtro(id,nome) values(3,'Rascunhos');
insert into filtro(id,nome) values(4,'Excluídos');
