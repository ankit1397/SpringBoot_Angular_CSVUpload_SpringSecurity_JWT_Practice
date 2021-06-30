import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CSVUploadComponent } from './csvupload.component';

describe('CSVUploadComponent', () => {
  let component: CSVUploadComponent;
  let fixture: ComponentFixture<CSVUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CSVUploadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CSVUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
