# I - How to install Rabbit-MQ with classique option ?
1 - download rabbit-mq from this URL :
[Rabbit-MQ](https://www.rabbitmq.com/install-windows.html)

2 - download dependency ErLang if install type is manual (see [manual-install](https://www.rabbitmq.com/install-windows-manual.html))
otherwise if you choose the first option you don't need to install ErLang (Once both Erlang and RabbitMQ have been installed)

3 - Open the command line (administrator) inside the folder C:\Program Files\RabbitMQ Server\rabbitmq_server-xxx\sbin
And Execute this commands to start rabbit-MQ server : 
* rabbitmq-plugins enable rabbitmq_management --offline
* rabbitmq-server.bat start

if you want to install service windows :
* rabbitmq-service install

if you want to enable any plugin you should copy the file inside the folder plugin and execute the command(see rabbit-docker) :
 * rabbitmq-plugins enable rabbitmq_delayed_message_exchange

# II - How to use Rabbit-MQ with docker
look at the folder rabbitmq-docker in this project
docker file you can execute the commands :
1 - docker build -t rabbitmq:3.10 .
2 - docker run -d --name rabbitmq_3_10 -p 5672:5672 -p 15672:15672 rabbitmq:3.10

Or you can also use docker compose see docker-compose.yml file :
1 - modify ligne 3 in dockerfile :
COPY rabbitmq-docker/rabbitmq_delayed_message_exchange-3.10.2.ez /opt/rabbitmq/plugins/rabbitmq_delayed_message_exchange-3.10.2.ez
2 - take this position with command line and execute :
docker compose up

You can access to Rabbit-mq server : http://localhost:15672/

the plugin rabbitmq_delayed_message_exchange is not installed by default that's why I chose it to show you how to install it :)