INSERT INTO `cliente` (`idcliente`, `direccion`, `eliminado`, `fcreate` ,`razonsocial`, `ruc`, `telefono`, `ucreate`) VALUES
  ('1', 'Av. Nicolás de Piérola, Cercado de Lima 15001 - Cuadra 21', b'0', '2021-07-10 15:39:37.524551' ,'LAIVE S.A', '20100095450', '016187600', 'SuperAdmin'),
  ('2', 'Av. Pananamericana Norte N° 419 Urb. Parque Gran, Chavin, Chimbote 02800', b'0', '2021-07-13 15:39:37.524551' ,'SODIMAC PERU S.A', '20112273922', '012030420', 'SuperAdmin'),
  ('3', 'Av. José Pardo 4694, Chimbote 02800', b'0', '2021-07-16 15:39:37.524551' ,'SAGA FALABELLA S.A', '20100128056', '015123347', 'SuperAdmin');

INSERT INTO `documentocliente` (`iddocumentocliente`, `descripcion`, `eliminado`, `fcreate` ,`nombre`, `ucreate`,`url`, `idcliente`) VALUES
  ('1', 'Documentos relacionados a la compra de productos alimenticios', b'0', '2021-07-10 15:39:37.524551' ,'docs_laive', 'SuperAdmin', 'documento_compra', '1'),
  ('2', 'Fichas documentarias para la negocación de productos de construcción', b'0', '2021-07-13 15:39:37.524551' ,'docs_sodimac', 'SuperAdmin', 'documento_negocio', '2'),
  ('3','Documentos saga anexado', b'0', '2021-07-16 15:39:37.524551' ,'docs_saga','SuperAdmin','documento_anexo', '3');

INSERT INTO `contacto` (`idcontacto`, `celular`,`correo`, `cargo`, `unidad`, `eliminado`, `fcreate` ,`nombre`, `telefono`, `ucreate`, `fupdate`, `uupdate`, `idcliente`) VALUES
  ('1', '235145121' ,'antonio_estrada1996@gmail.com', 'Cargo A', 'Unidad A', b'0', '2021-07-10 15:39:37.524551' ,'Antonio Campos Estrada Bonillo', '013142512','SuperAdmin', NULL, NULL, '1'),
  ('2', '565452125' ,'salazar_erp120@gmail.com', 'Cargo B', 'Unidad A', b'0', '2021-07-13 15:39:37.524551' ,'Jorge Campos Avellaneda Salazar', '016558745','SuperAdmin', NULL, NULL, '2'),
  ('3', '636562365' ,'raimundo_sotil656@hotmail.com', 'Cargo C', 'Unidad B', b'0', '2021-07-16 15:39:37.524551' ,'Astudillo Raimundo Peña Sotil', '016598721','SuperAdmin', NULL, NULL, '3');

INSERT INTO `spc` (`idspc`, `codigo`, `eliminado`, `fcreate`, `fechaabsolucion`, `fechaentrega`, `fechaenviodeconsulta`, `fechareunion`, `fechavisitatecnica`, `fupdate`, `proyecto`, `tipo`, `ubicacion`, `ucreate`, `uupdate`, `vendedor`, `idcliente`) VALUES
  (1, 'SPC-010', b'0', '2021-07-01 00:00:00.000000', '2021-07-10', '2021-07-15', '2021-07-20', '2021-07-21', '2021-07-23', NULL, 'Proyecto irrigación plan alto ', 'Suma Alzada', 'Ubicacion 1', 'SuperAdmin', NULL, 'Marco Otero Villanueva', 1),
  (2, 'SPC-011', b'0', '2021-07-01 00:00:00.000000', '2021-07-10', '2021-07-15', '2021-07-20', '2021-07-21', '2021-07-23', NULL, 'Proyecto textil plan bajo', 'Precios Unitarios', 'Ubicacion 2', 'SuperAdmin', NULL, 'Jorge Selva Mata', 2),
  (3, 'SPC-012', b'0', '2021-07-01 00:00:00.000000', '2021-07-10', '2021-07-15', '2021-07-20', '2021-07-21', '2021-07-23', NULL, 'Proyecto sal de maras', 'Suma Alzada', 'Ubicacion 3', 'SuperAdmin', NULL, 'Ricardo Bueno Balbis', 3),
  (4, 'SPC-013', b'0', '2021-07-01 00:00:00.000000', '2021-07-29', '2021-07-29', '2021-07-26', '2021-07-31', '2021-07-30', NULL, '2323232', 'Precios Unitarios', 'dsdsd', 'SuperAdmin', NULL, 'Vendedor Test', 1);

INSERT INTO `seguimientospc` (`idseguimientospc`, `descripcion`, `fcreate`, `formarecepcion`, `fupdate`, `observacion`, `ucreate`, `uupdate`, `idspc`) VALUES
  (NULL, 'Desc', '2021-08-02 00:00:00.000000', 'USB', NULL, 'Obsv', 'Super Admin', NULL, '4');

INSERT INTO `spccontacto` (`idspccontacto`, `ocupacion`, `idcontacto`, `idspc`) VALUES
  ('1', 'consulta', '1', '1'),
  ('2', 'tecnica', '2', '2'),
  ('3', 'visita', '3', '3');

INSERT INTO `ot` (`idot`, `codigo`, `eliminado`, `fcreate`, `otprincipal`, `tipoproyecto`, `ucreate`, `idspc`) VALUES
  ('1', 'OT-0001', b'0', '2021-07-10 00:00:00.000000', NULL , b'0', 'SuperAdmin', '1'),
  ('2', 'OT-0002', b'0', '2021-07-12 00:00:00.000000', NULL , b'0', 'SuperAdmin', '2');

INSERT INTO `unidad` (`idunidad`, `descripción`, `eliminado`, `fcreate`, `fupdate`, `nombre`, `ucreate`, `uupdate`, `idcliente`) VALUES
  (NULL, 'Desc A', b'0', '2021-07-01 00:00:00.000000', NULL, 'Unidad A', 'Super Admin', NULL, '1'),
  (NULL, 'Desc B', b'0', '2021-07-01 00:00:00.000000', NULL, 'Unidad B', 'Super Admin', NULL, '1'),
  (NULL, 'Desc C', b'0', '2021-07-01 00:00:00.000000', NULL, 'Unidad C', 'Super Admin', NULL, '2'),
  (NULL, 'Desc D', b'0', '2021-07-01 00:00:00.000000', NULL, 'Unidad D', 'Super Admin', NULL, '2'),
  (NULL, 'Desc E', b'0', '2021-07-01 00:00:00.000000', NULL, 'Unidad E', 'Super Admin', NULL, '3');
