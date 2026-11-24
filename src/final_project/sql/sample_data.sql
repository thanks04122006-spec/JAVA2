-- Run once in the schema that contains the cookie factory tables.
-- The SAMPLE- prefix identifies the rows created by this script.
INSERT INTO Client (CLI_id, CLI_name, CLI_phone, CLI_address)
VALUES ('SAMPLE-C01', 'Sample Cookie Shop', '010-0000-0000', 'Sample address');

INSERT INTO Production (PRD_id, PRD_date, PRD_target_qty, PRD_actual_qty)
VALUES ('SAMPLE-P01', DATE '2026-11-20', 1000, 0);

INSERT INTO Production (PRD_id, PRD_date, PRD_target_qty, PRD_actual_qty)
VALUES ('SAMPLE-P02', DATE '2026-11-21', 1000, 980);

INSERT INTO Production (PRD_id, PRD_date, PRD_target_qty, PRD_actual_qty)
VALUES ('SAMPLE-P03', DATE '2026-11-22', 1200, 1200);

INSERT INTO Production (PRD_id, PRD_date, PRD_target_qty, PRD_actual_qty)
VALUES ('SAMPLE-P04', DATE '2026-11-23', 500, 500);

INSERT INTO Packaging (PKG_prod_id, PKG_id, PKG_client_id, PKG_date, PKG_unit_qty, PKG_qty)
VALUES ('SAMPLE-P03', 1, 'SAMPLE-C01', DATE '2026-11-22', 12, 50);

INSERT INTO Packaging (PKG_prod_id, PKG_id, PKG_client_id, PKG_date, PKG_unit_qty, PKG_qty)
VALUES ('SAMPLE-P03', 2, 'SAMPLE-C01', DATE '2026-11-23', 10, 40);

INSERT INTO Packaging (PKG_prod_id, PKG_id, PKG_client_id, PKG_date, PKG_unit_qty, PKG_qty)
VALUES ('SAMPLE-P04', 1, 'SAMPLE-C01', DATE '2026-11-23', 10, 50);

INSERT INTO Shipment (SHP_prod_id, SHP_pack_id, SHP_client_id, SHP_pack_date, SHP_date, SHP_qty)
VALUES ('SAMPLE-P03', 1, 'SAMPLE-C01', DATE '2026-11-22', DATE '2026-11-23', 50);

INSERT INTO Shipment (SHP_prod_id, SHP_pack_id, SHP_client_id, SHP_pack_date, SHP_date, SHP_qty)
VALUES ('SAMPLE-P04', 1, 'SAMPLE-C01', DATE '2026-11-23', DATE '2026-11-24', 50);

COMMIT;
