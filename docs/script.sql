CREATE TABLE IF NOT EXISTS usuarios (
    id serial PRIMARY KEY,
    email varchar UNIQUE,
    senha varchar,
    data_criacao timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS paises (
    id serial PRIMARY KEY,
    nome varchar,
    sigla varchar
);

CREATE TABLE IF NOT EXISTS estados (
    id serial PRIMARY KEY,
    pais_id integer,
    nome varchar,
    sigla varchar,
    FOREIGN KEY (pais_id) REFERENCES paises (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cidades (
    id serial PRIMARY KEY,
    estados_id integer,
    nome varchar,
    FOREIGN KEY (estados_id) REFERENCES estados (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS candidatos (
    usuarios_id integer PRIMARY KEY,
    nome varchar,
    sobrenome varchar,
    cpf varchar UNIQUE,
    descricao varchar,
    cidades_id integer,
    cep varchar,
    formacao varchar,
    data_nascimento date,
    linkedin varchar,
    FOREIGN KEY (usuarios_id) REFERENCES usuarios (id) ON DELETE CASCADE,
    FOREIGN KEY (cidades_id) REFERENCES cidades (id)
);

CREATE TABLE IF NOT EXISTS empresas (
    usuarios_id integer PRIMARY KEY,
    razao_social varchar,
    cnpj varchar UNIQUE,
    descricao varchar,
    cidades_id integer,
    cep varchar,
    data_fundacao date,
    FOREIGN KEY (usuarios_id) REFERENCES usuarios (id) ON DELETE CASCADE,
    FOREIGN KEY (cidades_id) REFERENCES cidades (id)
);

CREATE TABLE IF NOT EXISTS competencias (
    id serial PRIMARY KEY,
    nome varchar
);

CREATE TABLE IF NOT EXISTS candidatos_competencias (
    candidatos_id integer,
    competencias_id integer,
    PRIMARY KEY (candidatos_id, competencias_id),
    FOREIGN KEY (candidatos_id) REFERENCES candidatos (usuarios_id) ON DELETE CASCADE,
    FOREIGN KEY (competencias_id) REFERENCES competencias (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS modalidades (
    id serial PRIMARY KEY,
    nome varchar
);

CREATE TABLE IF NOT EXISTS vagas (
    id serial PRIMARY KEY,
    empresas_id integer,
    titulo varchar,
    descricao varchar,
    modalidades_id integer,
    cidades_id integer,
    FOREIGN KEY (empresas_id) REFERENCES empresas (usuarios_id) ON DELETE CASCADE,
    FOREIGN KEY (modalidades_id) REFERENCES modalidades (id) ON DELETE CASCADE,
    FOREIGN KEY (cidades_id) REFERENCES cidades (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS vagas_competencias (
    vagas_id integer,
    competencias_id integer,
    PRIMARY KEY (vagas_id, competencias_id),
    FOREIGN KEY (vagas_id) REFERENCES vagas (id) ON DELETE CASCADE,
    FOREIGN KEY (competencias_id) REFERENCES competencias (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS candidatos_vagas (
    id serial PRIMARY KEY,
    vagas_id integer,
    candidatos_id integer,
    curtida boolean,
    data_curtida timestamp DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (vagas_id) REFERENCES vagas (id) ON DELETE CASCADE,
    FOREIGN KEY (candidatos_id) REFERENCES candidatos (usuarios_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS empresas_candidatos (
    id serial PRIMARY KEY,
    candidatos_id integer,
    empresas_id integer,
    curtida boolean,
    data_curtida timestamp DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (candidatos_id) REFERENCES candidatos (usuarios_id) ON DELETE CASCADE,
    FOREIGN KEY (empresas_id) REFERENCES empresas (usuarios_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS correspondencias (
    id serial PRIMARY KEY,
    empresas_candidatos_id integer,
    candidatos_vagas_id integer,
    FOREIGN KEY (empresas_candidatos_id) REFERENCES empresas_candidatos (id) ON DELETE CASCADE,
    FOREIGN KEY (candidatos_vagas_id) REFERENCES candidatos_vagas (id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION criar_correspondencia_candidato_vaga()
RETURNS TRIGGER AS $$
DECLARE
  empresas_candidatos_id INTEGER;
BEGIN
  IF NEW.curtida = TRUE 
  THEN
    SELECT ec.id INTO empresas_candidatos_id
    FROM empresas_candidatos ec
    WHERE ec.candidatos_id = NEW.candidatos_id
      AND ec.empresas_id = (
      SELECT empresas_id FROM vagas WHERE id = NEW.vagas_id
    )
      AND ec.curtida = TRUE;

    IF empresas_candidatos_id IS NOT NULL THEN
      INSERT INTO correspondencias (empresas_candidatos_id, candidatos_vagas_id)
      VALUES (empresas_candidatos_id, NEW.id);
    END IF;
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER candidatos_vagas_trigger
AFTER INSERT ON candidatos_vagas
FOR EACH ROW
EXECUTE FUNCTION criar_correspondencia_candidato_vaga();

CREATE OR REPLACE FUNCTION criar_correspondencia_empresa_candidato()
RETURNS TRIGGER AS $$
DECLARE
  candidatos_vagas_id INTEGER;
BEGIN
  IF NEW.curtida = TRUE THEN
    SELECT cv.id INTO candidatos_vagas_id
    FROM candidatos_vagas cv
    WHERE cv.candidatos_id = NEW.candidatos_id
      AND cv.curtida = TRUE;
    
    IF candidatos_vagas_id IS NOT NULL THEN
      INSERT INTO correspondencias (empresas_candidatos_id, candidatos_vagas_id)
      VALUES (NEW.id, candidatos_vagas_id);
    END IF;
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER empresas_candidatos_trigger
AFTER INSERT ON empresas_candidatos
FOR EACH ROW
EXECUTE FUNCTION criar_correspondencia_empresa_candidato();

INSERT INTO usuarios (email, senha)
VALUES
  ('ana.silva@email.com', 'senha123'),
  ('carlos.rodrigues@email.com', 'senha456'),
  ('maria.souza@email.com', 'senha789'),
  ('pedro.almeida@email.com', 'senhaabc'),
  ('julia.machado@email.com', 'senhaxyz'),
  ('maria@gmail.com', 'senha123'),
  ('joao@hotmail.com', 'senhasegura'),
  ('ana@yahoo.com', 'minhasenha'),
  ('carlos@gmail.com', 'senhadificil'),
  ('laura@gmail.com', 'outrasenha'),
  ('pedro@hotmail.com', 'senhasupersegura');

INSERT INTO paises (nome, sigla)
VALUES
  ('Brasil', 'BR'),
  ('Estados Unidos', 'US'),
  ('Canadá', 'CA'),
  ('Reino Unido', 'UK'),
  ('Austrália', 'AU');

INSERT INTO estados (pais_id, nome, sigla)
VALUES
  (1, 'Minas Gerais', 'MG'),
  (2, 'São Paulo', 'SP'),
  (3, 'Rio de Janeiro', 'RJ'),
  (4, 'Bahia', 'BA'),
  (5, 'Santa Catarina', 'SC');

INSERT INTO cidades (estados_id, nome)
VALUES
  (1, 'Belo Horizonte'),
  (1, 'São Paulo'),
  (1, 'Rio de Janeiro'),
  (1, 'Salvador'),
  (1, 'Florianópolis');

INSERT INTO competencias (nome)
VALUES
  ('Desenvolvimento Web'),
  ('Data Science'),
  ('Redes de Computadores'),
  ('Design Gráfico'),
  ('Gestão de Projetos');

INSERT INTO empresas (usuarios_id, razao_social, cnpj, descricao, cidades_id, cep, data_fundacao)
VALUES
  (6, 'Tech Solutions Ltda', '123.456.789/0001-01', 'Desenvolvimento de software e soluções de TI.', 2, '04567-000', '2000-03-15'),
  (7, 'Vida e Saúde Hospital', '987.654.321/0001-02', 'Hospital referência em serviços de saúde.', 3, '10020-030', '1980-07-22'),
  (8, 'EcoClean Sustentabilidade', '456.789.012/0001-03', 'Limpeza ecológica e sustentável.', 4, '40030-040', '2010-11-10'),
  (9, 'SunPower Energia Solar', '567.890.123/0001-04', 'Energia solar renovável para residências e empresas.', 1, '30100-000', '2005-06-08'),
  (10, 'Food Express Delivery', '654.321.098/0001-05', 'Entrega de alimentos de restaurantes locais.', 5, '88025-000', '2018-04-30');

INSERT INTO candidatos (usuarios_id, nome, sobrenome, cpf, descricao, cidades_id, cep, formacao, data_nascimento, linkedin)
VALUES
  (1, 'Ana Carolina', 'Silva', '123.456.789-10', 'Desenvolvedora Full Stack com experiência em JavaScript e React.', 1, '30100-000', 'Bacharel em Ciência da Computação', '1990-05-12', 'linkedin.com/in/anacarolina'),
  (2, 'Carlos Alberto', 'Rodrigues', '987.654.321-98', 'Cientista de Dados especializado em análise de dados em saúde.', 2, '04567-000', 'Mestrado em Estatística', '1985-08-20', 'linkedin.com/in/carlosalberto'),
  (3, 'Maria Fernanda', 'Souza', '234.567.890-12', 'Engenheira de Redes com certificação Cisco e experiência em segurança de redes.', 3, '10020-030', 'Engenheira de Redes de Computadores', '1988-03-28', 'linkedin.com/in/mariafernanda'),
  (4, 'Pedro Henrique', 'Almeida', '345.678.901-23', 'Designer Gráfico Sênior com prêmios em design de interfaces de usuário.', 4, '40030-040', 'Bacharel em Design Gráfico', '1982-11-05', 'linkedin.com/in/pedroalmeida'),
  (5, 'Julia Machado', 'Machado', '456.789.012-34', 'Gerente de Projetos de TI com histórico de entrega de projetos de grande porte.', 5, '88025-000', 'MBA em Gestão de Projetos', '1987-06-15', 'linkedin.com/in/juliamachado');

INSERT INTO modalidades (nome)
VALUES
  ('Presencial'),
  ('Remoto'),
  ('Híbrido');

INSERT INTO vagas (empresas_id, titulo, descricao, modalidades_id, cidades_id)
VALUES
  (6, 'Desenvolvedor Full-Stack', 'Desenvolvimento de aplicativos web e móveis.', 1, 2),
  (6, 'Engenheiro de Software Sênior', 'Projeto e desenvolvimento de software de missão crítica.', 1, 2),
  (7, 'Engenheiro de Dados', 'Gerenciamento e análise de grandes volumes de dados.', 1, 3),
  (8, 'Analista de Segurança Cibernética', 'Proteção dos sistemas de informação da empresa contra ameaças cibernéticas.', 1, 4),
  (9, 'Desenvolvedor Python', 'Desenvolvimento de aplicativos Python de alta performance.', 1, 1);

INSERT INTO vagas_competencias (vagas_id, competencias_id)
VALUES
  (1, 1),
  (1, 2),
  (2, 1), 
  (2, 2), 
  (3, 3), 
  (4, 4), 
  (5, 2); 
 
INSERT INTO empresas_candidatos (candidatos_id, empresas_id, curtida)
VALUES
  (1, 6, TRUE),
  (2, 7, TRUE),
  (3, 8, TRUE),
  (4, 9, FALSE),
  (5, 10, FALSE);

INSERT INTO candidatos_vagas (vagas_id, candidatos_id, curtida)
VALUES
  (1, 1, TRUE),
  (1, 2, TRUE),
  (2, 3, FALSE),
  (3, 4, TRUE),
  (4, 5, FALSE),
  (5, 1, TRUE);
  
SELECT c.nome, e.razao_social FROM
correspondencias cs 
INNER JOIN
candidatos_vagas cv
ON cs.candidatos_vagas_id = cv.id
INNER JOIN 
empresas_candidatos ec
ON cs.empresas_candidatos_id = ec.id
INNER JOIN 
candidatos c
ON c.usuarios_id = cv.id
INNER JOIN 
empresas e
ON e.usuarios_id = ec.id
  

