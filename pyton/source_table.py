import mysql.connector
import json
import collections

conn=mysql.connector.connect(user='root', password='Hongying@2017!', host='127.0.0.1', database='source')  #2
cur=conn.cursor()
cur.execute("select * from source.feed_source")
rows=cur.fetchall()
#print "Display all rows", rows
result_list=[]
for row in rows:
    t={'source_name': row[0], 'channel': row[1], 'last_update_time': row[2]}
    result_list.append(t)

print "result = ", result_list

j=json.dumps(result_list)

with open('feed_source.js', 'w') as f:
    json.dump(result_list,f)

conn.close()
