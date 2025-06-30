import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoproprietaireListComponent } from './coproprietaire-list.component';

describe('CoproprietaireListComponent', () => {
  let component: CoproprietaireListComponent;
  let fixture: ComponentFixture<CoproprietaireListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoproprietaireListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoproprietaireListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
