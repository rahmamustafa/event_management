import { TestBed } from '@angular/core/testing';

import { EmailSubscriptionService } from './email-subscription.service';

describe('EmailSubscriptionService', () => {
  let service: EmailSubscriptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmailSubscriptionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
