import http from 'k6/http';
import {check} from 'k6';


export const options = {
    discardResponseBodies: true,
    scenarios: {
        contacts: {
            executor: 'constant-arrival-rate',
            duration: '1m',
            rate: 180, // external API limit to 180 req/min
            timeUnit: '1m',
            preAllocatedVUs: 50,
        },
    },
};

export default () => {
    let res = http.get('http://localhost:8080/quiz');
    check(res, { 'status was 200': (r) => r.status === 200 });
}

