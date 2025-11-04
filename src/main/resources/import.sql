-- Categories
INSERT INTO categories(id, name, description) VALUES (1, 'Cleaning', 'Home and office cleaning services');
INSERT INTO categories(id, name, description) VALUES (2, 'Plumbing', 'Fix leaks, install fixtures, and more');
INSERT INTO categories(id, name, description) VALUES (3, 'Electrical', 'Electrical installation and repairs');
INSERT INTO categories(id, name, description) VALUES (4, 'Logistics', 'Moving, delivery, and transport services');
INSERT INTO categories(id, name, description) VALUES (5, 'Furniture', 'Assembly and repair services');


-- Services
INSERT INTO services(id, name, description, price, category_id) VALUES (1,  'Basic House Cleaning', '2-hour cleaning session', 49.99, 1);
INSERT INTO services(id, name, description, price, category_id) VALUES (2,  'Deep Cleaning', 'Thorough cleaning for entire house', 129.00, 1);
INSERT INTO services(id, name, description, price, category_id) VALUES (9,  'Carpet Shampooing', 'Deep clean carpets up to 2 rooms', 89.00, 1);
INSERT INTO services(id, name, description, price, category_id) VALUES (3,  'Leak Fix', 'Fix a leaking faucet or pipe', 59.00, 2);
INSERT INTO services(id, name, description, price, category_id) VALUES (4,  'Sink Installation', 'Install a new Sink', 149.00, 2);
INSERT INTO services(id, name, description, price, category_id) VALUES (10, 'Water Heater Maintenance', 'Flush tank and inspect anode and valves', 99.00, 2);
INSERT INTO services(id, name, description, price, category_id) VALUES (5,  'Light Fixture Install', 'Install a ceiling light fixture', 79.00, 3);
INSERT INTO services(id, name, description, price, category_id) VALUES (6,  'Outlet Repair', 'Repair a malfunctioning outlet', 39.00, 3);
INSERT INTO services(id, name, description, price, category_id) VALUES (11, 'Ceiling Fan Installation', 'Install or replace a ceiling fan', 119.00, 3);
INSERT INTO services(id, name, description, price, category_id) VALUES (7,  'Furniture Moving', 'Move household furniture between locations', 24.99, 4);
INSERT INTO services(id, name, description, price, category_id) VALUES (8,  'Furniture Assembly', 'Assemble flat-pack furniture', 69.00, 5);
INSERT INTO services(id, name, description, price, category_id) VALUES (13, 'Furniture Repair', 'Repair chairs, tables, hinges, and joints', 79.00, 5);