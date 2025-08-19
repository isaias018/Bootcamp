CREATE TABLE "fases"(
    "id_fases" BIGINT NOT NULL,
    "nombre" VARCHAR(50) NOT NULL
);
ALTER TABLE
    "fases" ADD PRIMARY KEY("id_fases");
CREATE TABLE "grupos"(
    "id_grupos" BIGINT NOT NULL,
    "nombre" VARCHAR(5) NOT NULL
);
ALTER TABLE
    "grupos" ADD PRIMARY KEY("id_grupos");
CREATE TABLE "equipos"(
    "id_equipos" BIGINT NOT NULL,
    "nombre" VARCHAR(100) NULL,
    "pais" VARCHAR(100) NOT NULL,
    "id_grupo" INTEGER NOT NULL
);
ALTER TABLE
    "equipos" ADD PRIMARY KEY("id_equipos");
CREATE TABLE "partidos"(
    "id_partidos" BIGINT NOT NULL,
    "id_fase" INTEGER NOT NULL,
    "id_equipo1" INTEGER NOT NULL,
    "id_equipo2" INTEGER NOT NULL,
    "fecha" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "estadio" VARCHAR(100) NOT NULL
);
ALTER TABLE
    "partidos" ADD PRIMARY KEY("id_partidos");
CREATE TABLE "resultado"(
    "id_resultado" BIGINT NOT NULL,
    "id_partido" INTEGER NOT NULL,
    "goles_equipo1" INTEGER NOT NULL,
    "goles_equipo2" INTEGER NOT NULL,
    "penales" INTEGER NOT NULL
);
ALTER TABLE
    "resultado" ADD PRIMARY KEY("id_resultado");
ALTER TABLE
    "equipos" ADD CONSTRAINT "equipos_id_equipos_foreign" FOREIGN KEY("id_equipos") REFERENCES "grupos"("id_grupos");
ALTER TABLE
    "partidos" ADD CONSTRAINT "partidos_id_partidos_foreign" FOREIGN KEY("id_partidos") REFERENCES "equipos"("id_equipos");
ALTER TABLE
    "partidos" ADD CONSTRAINT "partidos_id_partidos_foreign" FOREIGN KEY("id_partidos") REFERENCES "fases"("id_fases");
ALTER TABLE
    "resultado" ADD CONSTRAINT "resultado_id_resultado_foreign" FOREIGN KEY("id_resultado") REFERENCES "partidos"("id_partidos");