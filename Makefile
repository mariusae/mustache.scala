default: push

publocal:
	sbt clean clean-lib update compile-antlr publish-release

push: publocal
	rsync -avz -e ssh publish/* monkey.org:Public/html/maven/
