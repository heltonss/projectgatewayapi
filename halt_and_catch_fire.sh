#!/bin/bash

if [ -f bootrun.pid ]; then
  PID=$(cat bootrun.pid)
  kill $PID
  rm bootrun.pid
  echo "Processo bootRun parado."
else
  echo "Arquivo bootrun.pid não encontrado. O processo bootRun pode não estar em execução."
fi

service_name="service-pecas"
route_name="pecas"
router_new_gateway="newgateway"
plugin_name="response-transformer"

curl -i -X DELETE http://localhost:8001/plugins/$plugin_name
curl -i -X DELETE http://localhost:8001/services/$service_name/routes/$route_name
curl -i -X DELETE http://localhost:8001/services/$service_name/routes/$router_new_gateway
curl -i -X DELETE http://localhost:8001/services/$service_name
