import random
from faker import Faker

fake = Faker()

fake_clients = Faker('en_US')

def generate_developer():
    with open('developer.sql', 'w') as f:
        generated_name_set = set()
        generated_publisher_set = set()

        # generate new records to insert
        for i in range(10000):
            if (i % 100 == 0):
                print(f'Generated {i * 100} records')

            values = []
            for j in range(100):
                # generate fake developer name? -> using company name
                name = fake.company()
                if name in generated_name_set:
                    name += f"_{i * 10000 + j}"
                else:
                    generated_name_set.add(name)
                name = name.replace("'", "''")

                # generate fake hq
                hq = fake.city()
                hq = hq.replace("'", "''")

                # generate fake publisher name? -> using company name
                publisher = fake.company()
                if publisher in generated_publisher_set:
                    publisher += f"_{i * 10000 + j}"
                else:
                    generated_publisher_set.add(publisher)
                publisher = publisher.replace("'", "''")

                # generate fake founded_in and revenue
                founded_in = random.randint(1983, 2023)
                revenue = random.randint(1, 8300)

                values.append(
                    f'(\'{name}\', \'{hq}\', \'{publisher}\', {founded_in}, {revenue})'
                )

            print(
                f'INSERT INTO DEVELOPER(name, hq, publisher, founded_in, revenue) VALUES {", ".join(values)};',
                file=f)

def generate_game():
    gameNames=["Crystalblast", "Dreamnite", "Clusterpoint", "Astrohunt", "Cyberscape", "Madzone", "Endorlife", "Alterforce",
               "Alphacell", "Datacraft", "Demonline", "Expoint", "Archeborne", "Alterborne", "Everstorm", "Blueside", "Cyberphobia",
               "Cloudnite", "Bladecraft", "Bloodback", "Brutalspace", "Grimcell", "Everstorm", "Deltaborne", "Bladenite", "Fusionwind",
               "Dreamcry", "Hellsite", "Archedoom", "Farzone", "Evoline", "Ultrasite", "Evoworks",  "Alphablaze", "Chromaspace",
               "Evoblaze", "Madplan", "Embersky", "Chromashot", "Deadlife", "Bulletfire", "Dragonscape", "Crossnite", "Crossside",
               "Crossmind", "Evorush", "Brutaldroid", "Castleworks", "Ultralife", "Dreadworks", "Farstorm", "Masterrage",
               "Endorwatch", "Fuseblaze", "Bladerain", "Grimrain", "Bloodside", "Bulletphobia", "Evoreign", "Masterchase",
               "Crosslight", "Evofire", "Dreamplan", "Blockspace", "Defworks", "Borderline", "Fireforce", "Castleline",
               "Warmind", "Grimforce", "Clusterworks", "Dataplan", "Firemania", "Ultraline", "Evermind", "Airzone",
               "Antiland", "Dragonlife", "Dreamfire", "Backside", "Endorheart", "Crossmind", "Deltafire", "Backsite",
               "Fusioncraft", "Deltaland", "Dreamgene", "Bulletfight", "Endorworks", "Blastersky", "Demonblast",
               "Defphobia", "Castledoom", "Alterwind", "Antidude", "Ultrafight", "Alphacell", "Alphaborne", "Masterlight",
                "Bodymind", "GTA", "FIFA", "Fortnite", "Call of Duty", "PUBG", "Counter-Strike", "Mafia", "Stardew Valley",
               "The last of us", "Homefront", "Battlefield", "Need for Speed", "The Crew", "Cars", "Plague Tale",
               "Kao the Kangaroo", "PES", "WWE", "Handball", "NFL", "LA Noire", "Saints Row", "Assassin's Creed",
               "Far cry"]

    with open('game.sql', 'w') as f:
        generated_name_set = set()

        # generate new records to insert
        for i in range(10000):
            if (i % 100 == 0):
                print(f'Generated {i * 100} records')

            values = []
            for j in range(100):
                # generate fake developer name? -> using company name
                name = random.choice(gameNames)
                if name in generated_name_set:
                    name += f"_{i * 10000 + j}"
                else:
                    generated_name_set.add(name)
                name = name.replace("'", "''")

                genre = random.choice(["Shooter", "Action adventure", "Simulation", "MOBA", "Sports", "Racing", "Strategy",
                                       "Battle royale", "Puzzle platform", "Fighting", "Action platform", "Online board games"])
                genre = genre.replace("'", "''")
                modes = random.choice(["SP", "MP", "SP/MP", "CO-OP", "SP/MP/CO-OP", "MP/CO-OP", "SP/CO-OP"])
                modes = modes.replace("'", "''")

                # generate fake yearOfRelease, price and developer_id
                year_of_release = random.randint(1983, 2023)
                price = random.uniform(1, 90)
                developer_id = random.randint(1, 1000000)

                values.append(
                    f'(\'{name}\', \'{genre}\', \'{modes}\', {year_of_release}, {price}, {developer_id})'
                )

            print(
                f'INSERT INTO GAME(name, genre, modes, year_of_release, price, developer_id) VALUES {", ".join(values)};',
                file=f)


def generate_customers():
    with open('customers.sql', 'w') as f:
        for i in range(1000):
            print(f"Generated {i*1000} records")
            f.writelines("INSERT INTO CUSTOMER(first_name, last_name, email, adress, phone_number) VALUES ")
            current_batch_values = ""
            for j in range(999):
                current_batch_values += "(" + "'" + fake.first_name() + "'" + "," + "'" + fake.last_name() + "'" + "," + "'" + fake.unique.email()+ "'" + "," + "'" \
                                        + fake_clients.address()+ "'" + "," + "'" + str(
                    fake.unique.phone_number()) + "'" + "),"
            f.writelines(current_batch_values)
            f.writelines("(" +
                         "'" + fake.first_name() + "'" + "," +
                         "'" + fake.last_name() + "'" + "," +
                         "'" + fake.unique.email() + "'" + "," +
                         "'" + fake_clients.address() + "'" + "," +
                         "'" + str(fake.unique.phone_number()) + "'" + ");\n"
                         )

def generate_transaction():
    with open('transaction.sql', 'w') as f:

        generated_pairs = set()

        # generate new records to insert
        for i in range(10000):
            if (i % 1000 == 0):
                print(f'Generated {i * 1000} records')

            values = []
            for j in range(1000):

                game_id = random.randint(1, 1000000)
                customer_id = random.randint(1, 1000000)

                while (game_id, customer_id) in generated_pairs:
                    game_id = random.randint(1, 1000000)
                    customer_id = random.randint(1, 1000000)

                format = random.choice(["digital", "physical"])
                format = format.replace("'", "''")

                quantity = random.randint(1, 1000)

                values.append(
                    f'({game_id}, {customer_id}, \'{format}\', {quantity})'
                )

            print(
                f'INSERT INTO transaction (game_id, customer_id, format, quantity) VALUES {", ".join(values)};',
                file=f)

def generate_description_for_games():
    game_tags = ['Action-packed', 'Open world', 'Multiplayer', 'Strategy' ,'Sci-fi', 'Fantasy' ,'Survival', 'Adventure',
                        'Horror', 'Racing', 'Puzzle', 'Stealth', 'Platformer', 'First-person', 'Third-person', 'Exploration',
                        'Tactical', 'RPG', 'Simulation', 'Sandbox', 'Casual', 'Educational', 'Historical', 'Cyberpunk',
                        'Post-apocalyptic', 'Futuristic', 'Noir', 'Sports', 'Military', 'Comedy', 'Curse', 'Illuminati',
                 'World-War 2', 'Meme', 'LGBTQ+']

    query = []
    for i in range(1000000):
        query.append("UPDATE game SET description=" + "'" +
                     str(fake.sentence(nb_words=10, ext_word_list=game_tags)) + "'" +
                     " WHERE id={}".format(i) +  ";")

    with open('descriptions.sql', 'w') as f:
        f.write(''.join(query))



# generate_developer()
# generate_game()
# generate_transaction()
# generate_customers()
generate_description_for_games()
