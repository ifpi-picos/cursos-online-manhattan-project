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
    StatusCurso VARCHAR(255) NOT NULL,
    carga_Horaria INT NOT NULL,
);

-- Tabela Alunos
CREATE TABLE Alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
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
    nota Array PRECISION,    
    PRIMARY KEY (aluno_id, curso_id)
);

