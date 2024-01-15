import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DwButtonComponent } from './dw-button.component';

describe('DwButtonComponent', () => {
  let component: DwButtonComponent;
  let fixture: ComponentFixture<DwButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DwButtonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DwButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
