import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SyndicFormComponent } from './syndic-form.component';

describe('SyndicFormComponent', () => {
  let component: SyndicFormComponent;
  let fixture: ComponentFixture<SyndicFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SyndicFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SyndicFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
