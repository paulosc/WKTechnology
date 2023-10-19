#!/bin/bash
# wait-for-it.sh
# Script para aguardar a disponibilidade de um serviço remoto antes de iniciar outro serviço.

set -e

host="$1"
port="$2"
shift 2
cmd="$@"

>&2 echo "Aguardando $host:$port..."

while ! nc -z "$host" "$port"; do
  sleep 1
done

>&2 echo "$host:$port está disponível"

exec $cmd
