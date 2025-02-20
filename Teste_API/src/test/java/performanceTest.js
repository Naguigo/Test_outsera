import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 100 }, // Aumenta gradualmente para 100 usuários em 1 minuto
    { duration: '3m', target: 500 }, // Mantém 500 usuários por 3 minutos
    { duration: '1m', target: 0 },   // Reduz gradualmente para 0 usuários em 1 minuto
  ],
  thresholds: {
    http_req_duration: ['p(95)<500'], // 95% das requisições devem ser menores que 500ms
    http_req_failed: ['rate<0.01'],   // Taxa de falhas deve ser menor que 1%
  },
};

export default function () {
  const url = 'https://jsonplaceholder.typicode.com/posts'; // API pública de exemplo
  const res = http.get(url);

  // Verifica se o status da resposta é 200
  check(res, {
    'status is 200': (r) => r.status === 200,
  });

  sleep(1); // Simula um tempo de espera entre as requisições
}