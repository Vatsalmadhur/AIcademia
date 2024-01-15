import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LnButtonComponent } from './ln-button.component';

describe('LnButtonComponent', () => {
  let component: LnButtonComponent;
  let fixture: ComponentFixture<LnButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LnButtonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LnButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
