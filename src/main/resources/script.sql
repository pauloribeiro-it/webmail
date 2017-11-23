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
  id_filtro integer,
  id_destinatario integer,
  constraint pk_email primary key (id),
  constraint fk_email_remetente foreign key(id_remetente) references usuario(id),
  constraint fk_email_filtro foreign key(id_filtro) references filtro(id),
  constraint fk_usuario_destinatario foreign key(id_destinatario) references usuario(id)
);

create table auditoria_login(
  id integer auto_increment,
  id_sessao varchar(100),
  id_usuario integer,
  data_login timestamp,
  constraint pk_auditoria_login primary key (id),
  constraint fk_auditoria_login_usuario foreign key(id_usuario) references usuario(id)
);

create table auditoria_operacao(
  id integer auto_increment,
  id_auditoria_login integer,
  desc_operacao varchar(500),
  data_operacao timestamp,
  descricao_erro varchar(200),
  constraint pk_auditoria_operacao primary key(id),
  constraint fk_auditoria_login foreign key(id_auditoria_login) references auditoria_login(id)
);

insert into filtro(id,nome) values(1,'Caixa de Entrada');
insert into filtro(id,nome) values(2,'Lixo');
insert into filtro(id,nome) values(3,'Rascunhos');
