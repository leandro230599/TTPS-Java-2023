Para ejecutar la interfaz se le debe asignar permisos
sudo chmod 755 /opt/lampp/manager-linux-x64.run  


Para ejecutar se debe usar XAMPP 
sudo /opt/lampp/manager-linux-x64.run 
Ir a manage servers y darle a start all o stop all para finalizar

Si genera problemas apache
sudo /etc/init.d/apache2 stop
Si genera problemas MySQL
sudo /etc/init.d/mysql stop