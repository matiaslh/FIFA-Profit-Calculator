import fut

players = fut.core.players()
nations = fut.core.nations()

text_file = open("playerDatabase.txt", "w")

for key in players:
	print players[key]
	nationalityNum = players[key]['nationality']
	nation = nations[nationalityNum]
	string = str(key)+","+players[key]['firstname']+","+players[key]['lastname']+","+str(players[key]['rating'])+","+nation+"\n"
	text_file.write(string.encode('utf-8'))

text_file.close()
