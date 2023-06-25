import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {QuestionService} from './question.service';
import {mockQuestions} from '../../mock-questions';

describe('QuestionServiceService', () => {
  let service: QuestionService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [QuestionService]
    });

    service = TestBed.inject(QuestionService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });


  it('should be created', () => {
    const service: QuestionService = TestBed.get(QuestionService);
    expect(service).toBeTruthy();
  });

  it('findAll() should return expected questions', () => {
    service.findAll().subscribe(questions => {
      expect(questions.length).toBe(2);
      expect(questions).toEqual(mockQuestions);
    });

    const req = httpMock.expectOne('http://localhost:8080/quiz');

    expect(req.request.method).toBe('GET');

    req.flush(mockQuestions);
  });
});
