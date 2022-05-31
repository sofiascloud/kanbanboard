import { TestBed } from '@angular/core/testing';

import { SearchTaskService } from './search-task.service';

describe('SearchTaskService', () => {
  let service: SearchTaskService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchTaskService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
