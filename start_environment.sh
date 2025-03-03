#!/bin/bash
echo "subindo o ambiente"

# Start Kong
echo "iniciando o Kong"

service_name="service-pecas"
route_name="pecas"
router_new_gateway="newgateway"

echo "Criando o serviço $service_name"
echo ""
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
echo "criacao de servico executado com sucesso!"
echo ""
echo "#######################"
echo ""
echo "Criando a rota $route_name para o serviço $service_name"

curl -i -s -X POST http://localhost:8001/services/$service_name/routes \
  --data name=$route_name \
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
echo "criacao derota $route_name executado com sucesso!"
echo ""
echo "#######################"
echo ""
echo "Criando a rota $route_new_gateway para o serviço $service_name"
echo ""
curl -i -s -X POST http://localhost:8001/services/$service_name/routes \
  --data name=$router_new_gateway \
  --data protocols="http" \
  --data protocols="https" \
  --data strip_path=true \
  --data methods="POST" \
  --data hosts="localhost" \
  --data paths="/v1/newgateway" \
  --data https_redirect_status_code=426 \
  --data tags="new_gateway" \
  --data tags="remove_id" \
  --data tags="gateway"
  > /dev/null
echo ""
echo "criacao derota $router_new_gateway executado com sucesso!"
echo ""
echo "#######################"
echo ""
echo "Adicionando o plugin de response-transform para o serviço $service_name e rota $router_new_gateway"
echo ""
curl -i -s -X POST http://localhost:8001/routes/$router_new_gateway/plugins \
 --data "name=response-transformer" \
 --data "config.remove.json=id" \
 --data "instance_name=response_new_gateway" \
 --data "tags=remove_id" \
 > /dev/null
echo ""
echo "adicionado plugin com sucesso"
echo ""
echo "#######################"
echo ""
echo "subindo a api java"
# subindo a api java
./start_app.sh
