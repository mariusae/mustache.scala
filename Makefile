default: push

publocal:
	sbt clean publish

push: publocal
	rsync -avz -e ssh publish/* monkey.org:Public/html/maven/
