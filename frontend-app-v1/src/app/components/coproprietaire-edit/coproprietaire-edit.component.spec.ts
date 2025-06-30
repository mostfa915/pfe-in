import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoproprietaireEditComponent } from './coproprietaire-edit.component';

describe('CoproprietaireEditComponent', () => {
  let component: CoproprietaireEditComponent;
  let fixture: ComponentFixture<CoproprietaireEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoproprietaireEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoproprietaireEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
