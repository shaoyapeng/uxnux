import { post get } from '@/axios/http'

export function login(data = {}) {
  return new Promise((resolve, reject) => {
    post('/login', data)
      .then(response => {
        resolve(response.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}