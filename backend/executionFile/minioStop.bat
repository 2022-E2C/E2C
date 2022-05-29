@echo on
cd executionFile
mc alias set^
 minio http://localhost:9000 minioadmin minioadmin
mc admin service stop minio
exit