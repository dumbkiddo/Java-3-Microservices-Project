USE moviesinfo;

INSERT IGNORE INTO movies VALUES (1, 'Portrait of Lady on Fire');
INSERT IGNORE INTO movies VALUES (2, 'Eighth grade');
INSERT IGNORE INTO movies VALUES (3, 'The Farewell');
INSERT IGNORE INTO movies VALUES (4, 'The Invisible Man');
INSERT IGNORE INTO movies VALUES (5, 'Beautiful day in the Neighbourhood');

INSERT IGNORE INTO synopsis VALUES (1, 'The story of a forbidden affair between 
an aristocrat and a painter commissioned to paint a portrait.');
INSERT IGNORE INTO synopsis VALUES (2, 'Kayla, a teenager who struggles with anxiety 
strives to gain social acceptance from her peers.');
INSERT IGNORE INTO synopsis VALUES (3, 'A family discovers their grandmother has only a short 
while left to live and decide to keep her in the dark.');
INSERT IGNORE INTO synopsis VALUES (4, 'Trapped in relationship with her violent husband, 
Adrian Griffin searching for escape.');
INSERT IGNORE INTO synopsis VALUES (5, 'Based on the true story of a real-life friendship between Fred Rogers and journalist Tom Junod.');

INSERT IGNORE INTO movie_synopsis VALUES (1, 1);
INSERT IGNORE INTO movie_synopsis VALUES (2, 2);
INSERT IGNORE INTO movie_synopsis VALUES (3, 3);
INSERT IGNORE INTO movie_synopsis VALUES (4, 4);
INSERT IGNORE INTO movie_synopsis VALUES (5, 5);
