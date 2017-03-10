import mysql.connector
import json
import collections
encoding='utf-8'

conn=mysql.connector.connect(user='root', password='Hongying@2017!', host='127.0.0.1', database='source', use_unicode=True, charset='utf8')  #2
cur=conn.cursor()
#cur.execute("set character set 'utf8'")
#cur.execute('set names utf8')
#conn.charset = 'utf-8'
cur.execute("select * from source.message")
rows=cur.fetchall()

result_list=[]
for row in rows:
    t={'message_id': row[0],'source_name': row[1],'channel': row[2],'title': row[3],
       'creator': row[4],'link': row[5],'description': row[6],'contents': row[7],
       'timestamp': row[8],'number_of_images': row[9],'pub_date': row[10],'day_created': row[11]}
    result_list.append(t)

j=json.dumps(result_list)
message_file="message.js"


print "result = ", result_list

with open('message_data.js', 'w') as f:
    json.dump(result_list,f)

conn.close()
