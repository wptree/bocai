#!/bin/sh

DBName="bocai"
DBUser="root"  
DBPasswd="Bocai123"
BackupPath="/backup/mysql-backup"  
S3StorageBucket="s3://bocai-db-backup"
CurrentPath=$(pwd)
  
NewFile=bocai_$(date +%Y_%m_%d).tar.gz 
DumpFile=bocai_$(date +%Y_%m_%d).sql  
OldFile=$BackupPath/bocai_$(date +%Y_%m_%d --date='3 days ago').tar.gz  
  
  
#create backup directory
if [ ! -d $BackupPath ]; then  
    mkdir $BackupPath  
fi  
  
echo "---------------------------------------------------------------------"
echo $(date +"%y-%m-%d %H:%M:%S")  
echo "---------------------------------------------------------------------"  
  
#remove history file
if [ -f $OldFile ]; then  
  rm -f $OldFile
  s3cmd del $S3StorageBucket/$OldFile
  echo "[$OldFile] Delete Old File from S3 Success!"
  echo "[$OldFile] Delete Old File Success!" 
fi  
  
#new file
if [ -f $BackupPath/$NewFile ]; then  
    echo "[$NewFile] The Backup File is exists,Can't Backup! "
else  
    mysqldump --opt -u$DBUser -p$DBPasswd $DBName > $BackupPath/$DumpFile
    cd $BackupPath
    tar czvf $NewFile $DumpFile
    rm -rf $DumpFile
    echo "[$NewFile] Backup $DBName Success!"
    s3cmd put $NewFile $S3StorageBucket/bocai_$(date +%Y_%m_%d).tar.gz
    echo "[$NewFile] Transfer $DBName to S3 Success!"
    cd $CurrentPath
fi  

echo ""

exit 0

#Add to System timer
#crontab -e
#30 02 * * * /deploy/mysqlbk.sh >> /backup/mysql-backup/backup.log
#service crond restart


