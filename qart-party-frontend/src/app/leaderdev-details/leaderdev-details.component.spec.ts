import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaderdevDetailsComponent } from './leaderdev-details.component';

describe('LeaderdevDetailsComponent', () => {
  let component: LeaderdevDetailsComponent;
  let fixture: ComponentFixture<LeaderdevDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeaderdevDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LeaderdevDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
