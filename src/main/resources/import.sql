-- Categories
INSERT INTO categories(id, name, description) VALUES (1, 'Cleaning', 'Home and office cleaning services');
INSERT INTO categories(id, name, description) VALUES (2, 'Plumbing', 'Fix leaks, install fixtures, and more');
INSERT INTO categories(id, name, description) VALUES (3, 'Electrical', 'Electrical installation and repairs');

-- Services
INSERT INTO services(id, name, description, price, category_id) VALUES (1, 'Basic House Cleaning', '2-hour cleaning session', 49.99, 1);
INSERT INTO services(id, name, description, price, category_id) VALUES (2, 'Deep Cleaning', 'Thorough cleaning for entire house', 129.00, 1);
INSERT INTO services(id, name, description, price, category_id) VALUES (3, 'Leak Fix', 'Fix a leaking faucet or pipe', 59.00, 2);
INSERT INTO services(id, name, description, price, category_id) VALUES (4, 'Toilet Installation', 'Install a new toilet', 149.00, 2);
INSERT INTO services(id, name, description, price, category_id) VALUES (5, 'Light Fixture Install', 'Install a ceiling light fixture', 79.00, 3);
INSERT INTO services(id, name, description, price, category_id) VALUES (6, 'Outlet Repair', 'Repair a malfunctioning outlet', 39.00, 3);

