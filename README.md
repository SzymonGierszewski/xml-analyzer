# xml-analyzer
### REST API which can analyse the content of big XML files

##### HTTP request example:

curl -i -X POST \\
   -H "Content-Type:application/json" \\
   -d \\
'{
  "url": "https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml"
}' \\
 'http://localhost:8080/api/v1/analyzes/posts'


Docker image available at: https://hub.docker.com/r/szymongierszewski/xml-analyzer

Pull docker image: $ docker pull szymongierszewski/xml-analyzer

Pull docker image & create container: $ docker run -p <local_port>:8080 -it szymongierszewski/xml-analyzer

Stop docker container: $ docker stop <container_id_or_name>

Start docker container: $ docker start <container_id_or_name>
