import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {of} from 'rxjs';
import {RouterTestingModule} from '@angular/router/testing';
import {AppComponent} from './app.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {QuestionService} from "./question.service";
import {mockQuestions} from '../../mock-questions';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let questionService: QuestionService;
  let appTitle: String = 'Welcome to QuizMaster';

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
      ],
      declarations: [
        AppComponent
      ],
      providers: [
        QuestionService
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;

    questionService = TestBed.inject(QuestionService);
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'quizmaster-frontend'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('quizmaster-frontend');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain(appTitle);
  });

  it('getQuestions() should update questions array', () => {
    jest.spyOn(questionService, 'findAll').mockReturnValue(of(mockQuestions));
    component.getQuestions();

    expect(component.questions.length).toBe(2);
    expect(component.questions).toEqual(mockQuestions);
  });
});
