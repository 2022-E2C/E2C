@echo on
cd executionFile
mc alias set^
 minio http://localhost:9000 %MINIO_ROOT_USER% %MINIO_ROOT_PASSWORD%
mc admin service stop minio
exit