-- Tabela Professores
CREATE TABLE Professores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Tabela Cursos
CREATE TABLE Cursos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    carga_Horaria INT NOT NULL,
    descricao TEXT,
    professor_id INT REFERENCES Professores(id)
);

-- Tabela Alunos
CREATE TABLE Alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Tabela Turmas
CREATE TABLE Turmas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    periodo INT NOT NULL,
    horario VARCHAR(255) NOT NULL,
    professor_id INT REFERENCES Professores(id)
);

-- Tabela de associação para cursos que o professor ministra
CREATE TABLE Professor_Curso (
    professor_id INT REFERENCES Professores(id),
    curso_id INT REFERENCES Cursos(id),
    PRIMARY KEY (professor_id, curso_id)
);

-- Tabela de associação para cursos em que o aluno está matriculado
CREATE TABLE Aluno_Curso (
    aluno_id INT REFERENCES Alunos(id),
    curso_id INT REFERENCES Cursos(id),
    PRIMARY KEY (aluno_id, curso_id)
);

-- Tabela de associação para alunos em uma turma
CREATE TABLE Turma_Aluno (
    turma_id INT REFERENCES Turmas(id),
    aluno_id INT REFERENCES Alunos(id),
    PRIMARY KEY (turma_id, aluno_id)
);

-- Tabela de notas dos alunos em um curso
CREATE TABLE Notas (
    aluno_id INT REFERENCES Alunos(id),
    curso_id INT REFERENCES Cursos(id),
    nota DOUBLE PRECISION,
    PRIMARY KEY (aluno_id, curso_id)
);
