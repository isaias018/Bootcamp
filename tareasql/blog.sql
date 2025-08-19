CREATE TABLE "usuarios"(
    "id" BIGINT NOT NULL,
    "nombre" VARCHAR(30) NOT NULL,
    "email" VARCHAR(50) NOT NULL,
    "password" VARCHAR(20) NOT NULL,
    "fecha_registro" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "usuarios" ADD PRIMARY KEY("id");
CREATE TABLE "comentarios"(
    "id_comentario" BIGINT NOT NULL,
    "id_usuario" INTEGER NOT NULL,
    "id_post" INTEGER NOT NULL,
    "contenido" TEXT NOT NULL,
    "fecha_creacion" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "comentarios" ADD PRIMARY KEY("id_comentario");
CREATE TABLE "posteo"(
    "id_post" BIGINT NOT NULL,
    "id_usuario" INTEGER NOT NULL,
    "titulo" VARCHAR(50) NOT NULL,
    "contenido" TEXT NULL,
    "fecha_creacion" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "posteo" ADD PRIMARY KEY("id_post");
ALTER TABLE
    "comentarios" ADD CONSTRAINT "comentarios_id_usuario_foreign" FOREIGN KEY("id_usuario") REFERENCES "posteo"("id_post");
ALTER TABLE
    "posteo" ADD CONSTRAINT "posteo_id_usuario_foreign" FOREIGN KEY("id_usuario") REFERENCES "usuarios"("id");