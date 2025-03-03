#!/bin/bash
echo "subindo o ambiente"

# Start Kong
echo "iniciando o Kong"
echo "Criando "

service_name="service-pecas"

curl -i -s -X POST http://localhost:8001/services \
  --data name=$service_name \
  --data retries=3 \
  --data protocol=http \
  --data host=host.docker.internal \
  --data path=/api/v1/pecas \
  --data port=8080 \
  --data tags="services" \
  --data tags="peca-services"
 > /dev/null

echo "Curl executado com sucesso!"
curl -i -s -X POST http://localhost:8001/services/$service_name/routes \
  --data name=pecas \
  --data protocols="http" \
  --data protocols="https" \
  --data strip_path=true \
  --data methods="POST" \
  --data methods="GET" \
  --data hosts="localhost" \
  --data paths="/v1/gateway/pecas" \
  --data https_redirect_status_code=426 \
  --data tags="peca-routes" \
  --data tags="gateway"
  > /dev/null

# subindo a api java
./start_app.sh
