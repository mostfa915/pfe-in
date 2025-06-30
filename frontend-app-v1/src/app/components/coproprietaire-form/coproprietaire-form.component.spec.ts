import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoproprietaireFormComponent } from './coproprietaire-form.component';

describe('CoproprietaireFormComponent', () => {
  let component: CoproprietaireFormComponent;
  let fixture: ComponentFixture<CoproprietaireFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoproprietaireFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoproprietaireFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
