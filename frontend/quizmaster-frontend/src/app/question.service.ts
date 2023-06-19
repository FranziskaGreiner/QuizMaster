import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Question } from './question';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private readonly questionUrl: string;
  public questions: Question[];

  constructor(private http: HttpClient) {
    this.questionUrl = 'http://localhost:8080/quiz'
  }

  public findAll(): Observable<Question[]> {
    return this.http.get<Question[]>(this.questionUrl);
  }
}
