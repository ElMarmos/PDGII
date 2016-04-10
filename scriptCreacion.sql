-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema PDG
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema PDG
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PDG` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `PDG` ;

-- -----------------------------------------------------
-- Table `PDG`.`Paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Paciente` (
  `idPaciente` INT NOT NULL COMMENT '',
  `nombres` VARCHAR(50) NOT NULL COMMENT '',
  `apellidos` VARCHAR(50) NOT NULL COMMENT '',
  `tipoIdentificacion` VARCHAR(100) NOT NULL COMMENT '',
  `numeroIdentificacion` VARCHAR(100) NOT NULL COMMENT '',
  `numeroHistoriaClinica` VARCHAR(100) NOT NULL COMMENT '',
  `fechaNacimiento` DATE NOT NULL COMMENT '',
  `peso` DOUBLE NOT NULL COMMENT '',
  `talla` VARCHAR(45) NOT NULL COMMENT '',
  `sexo` VARCHAR(45) NOT NULL COMMENT '',
  `ciudadPrecediencia` VARCHAR(100) NOT NULL COMMENT '',
  `prioridad` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idPaciente`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Pabellon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Pabellon` (
  `idPabellon` INT NOT NULL COMMENT '',
  ` nombre` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`idPabellon`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Quirofano`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Quirofano` (
  `idQuirofano` INT NOT NULL COMMENT '',
  `nombreQuirofano` VARCHAR(50) NOT NULL COMMENT '',
  `idPabellon` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idQuirofano`)  COMMENT '',
  INDEX `fk_Quirofano_Pabellon1_idx` (`idPabellon` ASC)  COMMENT '',
  CONSTRAINT `fk_Quirofano_Pabellon1`
    FOREIGN KEY (`idPabellon`)
    REFERENCES `PDG`.`Pabellon` (`idPabellon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Cirugia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Cirugia` (
  `idCirugia` INT NOT NULL COMMENT '',
  `fechaIngreso` TIMESTAMP NOT NULL COMMENT '',
  `inicioIncision` TIMESTAMP NOT NULL COMMENT '',
  `horacierre` TIMESTAMP NOT NULL COMMENT '',
  `duracion` DOUBLE NOT NULL COMMENT '',
  `tipocirugia` VARCHAR(50) NOT NULL COMMENT '',
  `idPaciente` INT NOT NULL COMMENT '',
  `idQuirofano` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idCirugia`)  COMMENT '',
  INDEX `fk_Cirugia_Paciente1_idx` (`idPaciente` ASC)  COMMENT '',
  INDEX `fk_Cirugia_Quirofano1_idx` (`idQuirofano` ASC)  COMMENT '',
  CONSTRAINT `fk_Cirugia_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `PDG`.`Paciente` (`idPaciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cirugia_Quirofano1`
    FOREIGN KEY (`idQuirofano`)
    REFERENCES `PDG`.`Quirofano` (`idQuirofano`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Especialidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Especialidad` (
  `idEspecialidad` INT NOT NULL COMMENT '',
  `nombreEspecialidad` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`idEspecialidad`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Procedimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Procedimiento` (
  `codigoProcedimiento` INT NOT NULL COMMENT '',
  `procedimiento` TEXT NOT NULL COMMENT '',
  `idEspecialidad` INT NOT NULL COMMENT '',
  PRIMARY KEY (`codigoProcedimiento`)  COMMENT '',
  INDEX `fk_Procedimiento_Especialidad1_idx` (`idEspecialidad` ASC)  COMMENT '',
  CONSTRAINT `fk_Procedimiento_Especialidad1`
    FOREIGN KEY (`idEspecialidad`)
    REFERENCES `PDG`.`Especialidad` (`idEspecialidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`CirugiaProcedimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`CirugiaProcedimiento` (
  `idCirugia` INT NOT NULL COMMENT '',
  `codigoProcedimiento` INT NOT NULL COMMENT '',
  `horaInicio` TIME NOT NULL COMMENT '',
  `horaFin` TIME NOT NULL COMMENT '',
  `observacion` TEXT NULL COMMENT '',
  PRIMARY KEY (`idCirugia`, `codigoProcedimiento`)  COMMENT '',
  INDEX `fk_Cirugia_has_Procedimiento_Procedimiento1_idx` (`codigoProcedimiento` ASC)  COMMENT '',
  INDEX `fk_Cirugia_has_Procedimiento_Cirugia_idx` (`idCirugia` ASC)  COMMENT '',
  CONSTRAINT `fk_Cirugia_has_Procedimiento_Cirugia`
    FOREIGN KEY (`idCirugia`)
    REFERENCES `PDG`.`Cirugia` (`idCirugia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cirugia_has_Procedimiento_Procedimiento1`
    FOREIGN KEY (`codigoProcedimiento`)
    REFERENCES `PDG`.`Procedimiento` (`codigoProcedimiento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`ProfesionalSalud`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`ProfesionalSalud` (
  `idProfesionalSalud` INT NOT NULL COMMENT '',
  `apellidos` VARCHAR(100) NOT NULL COMMENT '',
  `nombres` VARCHAR(100) NOT NULL COMMENT '',
  `profesion` TEXT NOT NULL COMMENT '',
  `contrato` TEXT NOT NULL COMMENT '',
  PRIMARY KEY (`idProfesionalSalud`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`ProfesionalSaludEspecialidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`ProfesionalSaludEspecialidad` (
  `idProfesionalSalud` INT NOT NULL COMMENT '',
  `idEspecialidad` INT NOT NULL COMMENT '',
  `prioridad` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idProfesionalSalud`, `idEspecialidad`)  COMMENT '',
  INDEX `fk_ProfesionalSalud_has_Especialidad_Especialidad1_idx` (`idEspecialidad` ASC)  COMMENT '',
  INDEX `fk_ProfesionalSalud_has_Especialidad_ProfesionalSalud1_idx` (`idProfesionalSalud` ASC)  COMMENT '',
  CONSTRAINT `fk_ProfesionalSalud_has_Especialidad_ProfesionalSalud1`
    FOREIGN KEY (`idProfesionalSalud`)
    REFERENCES `PDG`.`ProfesionalSalud` (`idProfesionalSalud`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProfesionalSalud_has_Especialidad_Especialidad1`
    FOREIGN KEY (`idEspecialidad`)
    REFERENCES `PDG`.`Especialidad` (`idEspecialidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Anestesiologo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Anestesiologo` (
  `idAnestesiologo` INT NOT NULL COMMENT '',
  `idProfesionalSalud` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idAnestesiologo`)  COMMENT '',
  INDEX `fk_Anestesiologo_ProfesionalSalud1_idx` (`idProfesionalSalud` ASC)  COMMENT '',
  CONSTRAINT `fk_Anestesiologo_ProfesionalSalud1`
    FOREIGN KEY (`idProfesionalSalud`)
    REFERENCES `PDG`.`ProfesionalSalud` (`idProfesionalSalud`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`CirugiaProfesionalSalud`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`CirugiaProfesionalSalud` (
  `idCirugia` INT NOT NULL COMMENT '',
  `idProfesionalSalud` INT NOT NULL COMMENT '',
  `rol` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`idCirugia`, `idProfesionalSalud`)  COMMENT '',
  INDEX `fk_Cirugia_has_ProfesionalSalud_ProfesionalSalud1_idx` (`idProfesionalSalud` ASC)  COMMENT '',
  INDEX `fk_Cirugia_has_ProfesionalSalud_Cirugia1_idx` (`idCirugia` ASC)  COMMENT '',
  CONSTRAINT `fk_Cirugia_has_ProfesionalSalud_Cirugia1`
    FOREIGN KEY (`idCirugia`)
    REFERENCES `PDG`.`Cirugia` (`idCirugia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cirugia_has_ProfesionalSalud_ProfesionalSalud1`
    FOREIGN KEY (`idProfesionalSalud`)
    REFERENCES `PDG`.`ProfesionalSalud` (`idProfesionalSalud`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Recurso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Recurso` (
  `idRecurso` INT NOT NULL COMMENT '',
  `nombre` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`idRecurso`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Recurso_has_Cirugia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Recurso_has_Cirugia` (
  `idRecurso` INT NOT NULL COMMENT '',
  `idCirugia` INT NOT NULL COMMENT '',
  `cantidad` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idRecurso`, `idCirugia`)  COMMENT '',
  INDEX `fk_Recurso_has_Cirugia_Cirugia1_idx` (`idCirugia` ASC)  COMMENT '',
  INDEX `fk_Recurso_has_Cirugia_Recurso1_idx` (`idRecurso` ASC)  COMMENT '',
  CONSTRAINT `fk_Recurso_has_Cirugia_Recurso1`
    FOREIGN KEY (`idRecurso`)
    REFERENCES `PDG`.`Recurso` (`idRecurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recurso_has_Cirugia_Cirugia1`
    FOREIGN KEY (`idCirugia`)
    REFERENCES `PDG`.`Cirugia` (`idCirugia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`CIE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`CIE` (
  `idCIE` INT NOT NULL COMMENT '',
  `codigo` INT NOT NULL COMMENT '',
  `procedimiento` TEXT NOT NULL COMMENT '',
  PRIMARY KEY (`idCIE`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Cirugia_has_CIE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Cirugia_has_CIE` (
  `idCirugia` INT NOT NULL COMMENT '',
  `idCIE` INT NOT NULL COMMENT '',
  `horaInicio` TIME NOT NULL COMMENT '',
  `horaFin` TIME NOT NULL COMMENT '',
  `observacion` TEXT NULL COMMENT '',
  PRIMARY KEY (`idCirugia`, `idCIE`)  COMMENT '',
  INDEX `fk_Cirugia_has_CIE_CIE1_idx` (`idCIE` ASC)  COMMENT '',
  INDEX `fk_Cirugia_has_CIE_Cirugia1_idx` (`idCirugia` ASC)  COMMENT '',
  CONSTRAINT `fk_Cirugia_has_CIE_Cirugia1`
    FOREIGN KEY (`idCirugia`)
    REFERENCES `PDG`.`Cirugia` (`idCirugia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cirugia_has_CIE_CIE1`
    FOREIGN KEY (`idCIE`)
    REFERENCES `PDG`.`CIE` (`idCIE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Dotacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Dotacion` (
  `idDotacion` INT NOT NULL COMMENT '',
  `nombre` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`idDotacion`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Quirofano_has_Dotacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Quirofano_has_Dotacion` (
  `idQuirofano` INT NOT NULL COMMENT '',
  `idDotacion` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idQuirofano`, `idDotacion`)  COMMENT '',
  INDEX `fk_Quirofano_has_Dotacion_Dotacion1_idx` (`idDotacion` ASC)  COMMENT '',
  INDEX `fk_Quirofano_has_Dotacion_Quirofano1_idx` (`idQuirofano` ASC)  COMMENT '',
  CONSTRAINT `fk_Quirofano_has_Dotacion_Quirofano1`
    FOREIGN KEY (`idQuirofano`)
    REFERENCES `PDG`.`Quirofano` (`idQuirofano`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Quirofano_has_Dotacion_Dotacion1`
    FOREIGN KEY (`idDotacion`)
    REFERENCES `PDG`.`Dotacion` (`idDotacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`BloqueQuirurgico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`BloqueQuirurgico` (
  `idBloqueQuirurgico` INT NOT NULL COMMENT '',
  `nombreBloque` VARCHAR(50) NOT NULL COMMENT '',
  `horaInicio` TIMESTAMP NOT NULL COMMENT '',
  `horaFinal` TIMESTAMP NOT NULL COMMENT '',
  `habilitado` TINYINT(1) NOT NULL COMMENT '',
  `idEspecialidad` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idBloqueQuirurgico`)  COMMENT '',
  INDEX `fk_BloqueQuirurgico_Especialidad1_idx` (`idEspecialidad` ASC)  COMMENT '',
  CONSTRAINT `fk_BloqueQuirurgico_Especialidad1`
    FOREIGN KEY (`idEspecialidad`)
    REFERENCES `PDG`.`Especialidad` (`idEspecialidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`DisponibilidadProfesional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`DisponibilidadProfesional` (
  `idDisponibilidadProfesional` INT NOT NULL COMMENT '',
  `horaInicio` TIME NOT NULL COMMENT '',
  `horaFin` TIME NOT NULL COMMENT '',
  `habilitado` TINYINT(1) NOT NULL COMMENT '',
  `periodoInicio` TEXT NOT NULL COMMENT '',
  `periodoFin` TEXT NOT NULL COMMENT '',
  `repeticion` TEXT NOT NULL COMMENT '',
  `idProfesionalSalud` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idDisponibilidadProfesional`)  COMMENT '',
  INDEX `fk_DisponibilidadProfesional_ProfesionalSalud1_idx` (`idProfesionalSalud` ASC)  COMMENT '',
  CONSTRAINT `fk_DisponibilidadProfesional_ProfesionalSalud1`
    FOREIGN KEY (`idProfesionalSalud`)
    REFERENCES `PDG`.`ProfesionalSalud` (`idProfesionalSalud`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`BloqueDisponibilidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`BloqueDisponibilidad` (
  `idDisponibilidadProfesional` INT NOT NULL COMMENT '',
  `idBloqueQuirurgico` INT NOT NULL COMMENT '',
  `horaInicio` TIME NOT NULL COMMENT '',
  `horaFin` TIME NOT NULL COMMENT '',
  `fechaInicio` DATE NOT NULL COMMENT '',
  `fechaFin` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`idDisponibilidadProfesional`, `idBloqueQuirurgico`)  COMMENT '',
  INDEX `fk_DisponibilidadProfesional_has_BloqueQuirurgico_BloqueQui_idx` (`idBloqueQuirurgico` ASC)  COMMENT '',
  INDEX `fk_DisponibilidadProfesional_has_BloqueQuirurgico_Disponibi_idx` (`idDisponibilidadProfesional` ASC)  COMMENT '',
  CONSTRAINT `fk_DisponibilidadProfesional_has_BloqueQuirurgico_Disponibili1`
    FOREIGN KEY (`idDisponibilidadProfesional`)
    REFERENCES `PDG`.`DisponibilidadProfesional` (`idDisponibilidadProfesional`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DisponibilidadProfesional_has_BloqueQuirurgico_BloqueQuiru1`
    FOREIGN KEY (`idBloqueQuirurgico`)
    REFERENCES `PDG`.`BloqueQuirurgico` (`idBloqueQuirurgico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Cirujano`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Cirujano` (
  `idCirujano` INT NOT NULL COMMENT '',
  `idProfesionalSalud` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idCirujano`)  COMMENT '',
  INDEX `fk_Cirujano_ProfesionalSalud1_idx` (`idProfesionalSalud` ASC)  COMMENT '',
  CONSTRAINT `fk_Cirujano_ProfesionalSalud1`
    FOREIGN KEY (`idProfesionalSalud`)
    REFERENCES `PDG`.`ProfesionalSalud` (`idProfesionalSalud`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Solicitud`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Solicitud` (
  `idSolicitud` INT NOT NULL COMMENT '',
  `fechaSolicitud` TIMESTAMP NOT NULL COMMENT '',
  `fechaAtencion` TIMESTAMP NOT NULL COMMENT '',
  `fechaProgramacion` TIMESTAMP NOT NULL COMMENT '',
  `fechaCirugia` TIMESTAMP NOT NULL COMMENT '',
  `tipoPaciente` VARCHAR(50) NOT NULL COMMENT '',
  `jornadaPreferencia` VARCHAR(50) NOT NULL COMMENT '',
  `idPaciente` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idSolicitud`)  COMMENT '',
  INDEX `fk_Solicitud_Paciente1_idx` (`idPaciente` ASC)  COMMENT '',
  CONSTRAINT `fk_Solicitud_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `PDG`.`Paciente` (`idPaciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Solicitud_has_Procedimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Solicitud_has_Procedimiento` (
  `idSolicitud` INT NOT NULL COMMENT '',
  `codigoProcedimiento` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idSolicitud`, `codigoProcedimiento`)  COMMENT '',
  INDEX `fk_Solicitud_has_Procedimiento_Procedimiento1_idx` (`codigoProcedimiento` ASC)  COMMENT '',
  INDEX `fk_Solicitud_has_Procedimiento_Solicitud1_idx` (`idSolicitud` ASC)  COMMENT '',
  CONSTRAINT `fk_Solicitud_has_Procedimiento_Solicitud1`
    FOREIGN KEY (`idSolicitud`)
    REFERENCES `PDG`.`Solicitud` (`idSolicitud`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Solicitud_has_Procedimiento_Procedimiento1`
    FOREIGN KEY (`codigoProcedimiento`)
    REFERENCES `PDG`.`Procedimiento` (`codigoProcedimiento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Estado` (
  `idEstado` INT NOT NULL COMMENT '',
  `nombre` VARCHAR(50) NOT NULL COMMENT '',
  `descripcion` TEXT NOT NULL COMMENT '',
  PRIMARY KEY (`idEstado`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Seguimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Seguimiento` (
  `idPaciente` INT NOT NULL COMMENT '',
  `idEstado` INT NOT NULL COMMENT '',
  `fechaCambio` TIMESTAMP NOT NULL COMMENT '',
  PRIMARY KEY (`idPaciente`, `idEstado`)  COMMENT '',
  INDEX `fk_Paciente_has_Estado_Estado1_idx` (`idEstado` ASC)  COMMENT '',
  INDEX `fk_Paciente_has_Estado_Paciente1_idx` (`idPaciente` ASC)  COMMENT '',
  CONSTRAINT `fk_Paciente_has_Estado_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `PDG`.`Paciente` (`idPaciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paciente_has_Estado_Estado1`
    FOREIGN KEY (`idEstado`)
    REFERENCES `PDG`.`Estado` (`idEstado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Patologia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Patologia` (
  `idPatologia` INT NOT NULL COMMENT '',
  `nombre` VARCHAR(100) NOT NULL COMMENT '',
  PRIMARY KEY (`idPatologia`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Patientologia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Patientologia` (
  `idPatologia` INT NOT NULL COMMENT '',
  `idPaciente` INT NOT NULL COMMENT '',
  `nombre` VARCHAR(100) NOT NULL COMMENT '',
  PRIMARY KEY (`idPatologia`, `idPaciente`)  COMMENT '',
  INDEX `fk_Patologia_has_Paciente_Paciente1_idx` (`idPaciente` ASC)  COMMENT '',
  INDEX `fk_Patologia_has_Paciente_Patologia1_idx` (`idPatologia` ASC)  COMMENT '',
  CONSTRAINT `fk_Patologia_has_Paciente_Patologia1`
    FOREIGN KEY (`idPatologia`)
    REFERENCES `PDG`.`Patologia` (`idPatologia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Patologia_has_Paciente_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `PDG`.`Paciente` (`idPaciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`Cama`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`Cama` (
  `idCama` INT NOT NULL COMMENT '',
  `codigo` VARCHAR(50) NULL COMMENT '',
  `estado` VARCHAR(50) NULL COMMENT '',
  PRIMARY KEY (`idCama`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PDG`.`CamaPaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PDG`.`CamaPaciente` (
  `idCama` INT NOT NULL COMMENT '',
  `idPaciente` INT NOT NULL COMMENT '',
  `fechaIngreso` TIMESTAMP NOT NULL COMMENT '',
  `fechaSalida` TIMESTAMP NOT NULL COMMENT '',
  PRIMARY KEY (`idCama`, `idPaciente`)  COMMENT '',
  INDEX `fk_Cama_has_Paciente_Paciente1_idx` (`idPaciente` ASC)  COMMENT '',
  INDEX `fk_Cama_has_Paciente_Cama1_idx` (`idCama` ASC)  COMMENT '',
  CONSTRAINT `fk_Cama_has_Paciente_Cama1`
    FOREIGN KEY (`idCama`)
    REFERENCES `PDG`.`Cama` (`idCama`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cama_has_Paciente_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `PDG`.`Paciente` (`idPaciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
